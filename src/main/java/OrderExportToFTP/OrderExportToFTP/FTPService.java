package OrderExportToFTP.OrderExportToFTP;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FTPService {

	@Value("${ftp.server}")
	private String server;

	@Value("${ftp.port}")
	private int port;

	@Value("${ftp.user}")
	private String user;

	@Value("${ftp.pass}")
	private String pass;
	
	@Autowired
	private FTPClient ftpClient;
	
	public FTPService(FTPClient ftpClient ) {
		this.ftpClient = ftpClient;
	}
	
	
	

}
