package OrderExportToFTP.OrderExportToFTP.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import OrderExportToFTP.OrderExportToFTP.model.Order;
import OrderExportToFTP.OrderExportToFTP.model.OrderDetail;
import OrderExportToFTP.OrderExportToFTP.model.Product;
import OrderExportToFTP.OrderExportToFTP.model.User;

@Service
public class FileConverter {
	
	
	@Value("${csv.columns}")
	private String csvColumns;	
	
	@Value("${csv.defaultid}")
	private Long defaultid;

	public FileConverter() {
	}

	public File generateCSVFile(User user, String str) throws IOException {
		String dateFormatForFile = "yyyy-MM-dd HH-mm-ss";
		String dateString = new SimpleDateFormat(dateFormatForFile).format(new Date());
		String fileName = dateString + " - " + user.getUsername() + ".csv";
		File file = new File(fileName);

		FileWriter fw = new FileWriter(file);

		fw.write(str);
		fw.close();

		return file;
	}

	public File generateCSV(Order order) throws IOException {
		StringBuilder csvBuilder = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Header with semicolon separators
		csvBuilder.append(csvColumns).append("\n");

		User user = order.getUser();
		String orderDate = dateFormat.format(order.getOrderDate());
		String comment = order.getComment();
		//In case, the user doesn't exist in ERP yet.
		Long erpId = user.getErpId() == null ? defaultid : user.getErpId();

		for (OrderDetail detail : order.getOrderDetails()) {
			Product product = detail.getProduct();
			int pack = product.getPack();
			int quantity = detail.getQuantity();
			int cartons = quantity / pack; // Calculate full cartons
			int totalCartonQuantity = cartons * pack; // Total quantity for full cartons
			int remainingQuantity = quantity % pack; // Remaining quantity not filling a carton

			// Append line for full cartons
			if (cartons > 0) {
				csvBuilder.append(createCSVLine(
						erpId, 
						orderDate, 
						product.getProductId(),
						totalCartonQuantity, 
						cartons, 
						product.getGtinPack() == null ? product.getGtinUnit() : product.getGtinPack(), 
						comment));
			}

			// Append line for remaining quantity, if any
			if (remainingQuantity > 0) {
				csvBuilder.append(createCSVLine(
						erpId, 
						orderDate, 
						product.getProductId(),
						remainingQuantity, 
						0, 
						product.getGtinUnit(), 
						comment));
			}
		}

		return generateCSVFile(user, csvBuilder.toString());
	}

	private String createCSVLine(Long erpId, String orderDate, Long productId, int quantity, int carton,
			String gtinUnit, String comment) {
		return String.format("%d;%s;%d;%d;%d;%s;%s\n", erpId, orderDate, productId, quantity, carton, gtinUnit,
				comment);
	}
}
