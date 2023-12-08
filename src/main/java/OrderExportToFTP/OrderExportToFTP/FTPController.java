package OrderExportToFTP.OrderExportToFTP;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import OrderExportToFTP.OrderExportToFTP.model.Order;
import OrderExportToFTP.OrderExportToFTP.service.FTPService;
import OrderExportToFTP.OrderExportToFTP.service.FileConverter;
import OrderExportToFTP.OrderExportToFTP.service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ftpservice")
public class FTPController {

	@Autowired
	private FTPService ftpService;
	
	@Autowired
	private SecurityService securityService;

	@Autowired
	private FileConverter fileConverter;
	
	public FTPController(FTPService ftpService, SecurityService securityService, FileConverter fileConverter) {
		this.ftpService = ftpService;
		this.securityService = securityService;
		this.fileConverter = fileConverter;
	}

	@PostMapping("/sendorder")
	public ResponseEntity<String> sendFileToFTP(@RequestBody Order order, HttpServletRequest request) {
		boolean success = false;
		
		String apikey = request.getHeader("X-API-KEY");
		if (!securityService.apikeyIsValid(apikey)) 
			return new ResponseEntity<String>("Invalid Api Key", HttpStatus.BAD_REQUEST);
		
		File file;

		try {file = fileConverter.generateCSV(order);} 
		catch (IOException e) {e.printStackTrace(); return new ResponseEntity<String>("Unable to create csv file", HttpStatus.BAD_REQUEST);}
		
		success = ftpService.sendFileToFTPServer(file);
		
		if (!success)
			return new ResponseEntity<String>("Unable to send file to ftp server", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<String>("Successfully sended file to ftp server", HttpStatus.OK);
	}

}
