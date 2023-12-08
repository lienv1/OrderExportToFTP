package OrderExportToFTP.OrderExportToFTP.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Value("${ftp.remotepath}")
	private String remotePath;


	public FTPService() {
	}

	public boolean sendFileToFTPServer(File file) {
		FTPClient ftpClient = new FTPClient();
		boolean success = false;
		try {
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			String destFile = remotePath + file.getName();

			InputStream inputStream = new FileInputStream(file);

			success = ftpClient.storeFile(destFile, inputStream);

			inputStream.close();
			if (success)
				System.out.println("Bestellung eingegangen");

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		return success;
	}

}
