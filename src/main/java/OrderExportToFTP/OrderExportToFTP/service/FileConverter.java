package OrderExportToFTP.OrderExportToFTP.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import OrderExportToFTP.OrderExportToFTP.model.Order;
import OrderExportToFTP.OrderExportToFTP.model.OrderDetail;
import OrderExportToFTP.OrderExportToFTP.model.Product;
import OrderExportToFTP.OrderExportToFTP.model.User;

@Service
public class FileConverter {

	public FileConverter() {

	}
	
    public File generateCSV(Order order) throws IOException {
        StringBuilder csvBuilder = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Header with semicolon separators
        csvBuilder.append("username;orderDate;productId;quantity;carton;gtinUnit;comment\n");

        User user = order.getUser();
        String orderDate = dateFormat.format(order.getOrderDate());
        String comment = order.getComment();

        for (OrderDetail detail : order.getOrderDetails()) {
            Product product = detail.getProduct();
            int pack = product.getPack() == null ? 1 : product.getPack();
            int quantity = detail.getQuantity();
            int cartons = quantity / pack; // Calculate full cartons
            int remainingQuantity = quantity % pack; // Remaining quantity not filling a carton

            // Append lines for full cartons
            for (int i = 0; i < cartons; i++) {
                csvBuilder.append(createCSVLine(user.getUsername(), orderDate, product.getProductId(),
                        pack, 1, product.getGtinUnit(), comment));
            }

            // Append line for remaining quantity, if any
            if (remainingQuantity > 0) {
                csvBuilder.append(createCSVLine(user.getUsername(), orderDate, product.getProductId(),
                        remainingQuantity, 0, product.getGtinUnit(), comment));
            }
        }
        
        String dateFormatForFile = "yyyy-MM-dd HH-mm-ss";
		String dateString = new SimpleDateFormat(dateFormatForFile).format(new Date());
        String fileName = dateString + " - " + user.getUsername() + ".csv";
        File file = new File(fileName);
      
		FileWriter fw = new FileWriter(file);
		fw.write(csvBuilder.toString());
		fw.close();
	
        return file;
    }

    private String createCSVLine(String username, String orderDate, Long productId, int quantity,
                                        int carton, String gtinUnit, String comment) {
        return String.format("%s;%s;%d;%d;%d;%s;%s\n",
                username, orderDate, productId, quantity, carton, gtinUnit, comment);
    }
}
