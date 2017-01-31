package controllers;

import com.dynatrace.diagnostics.core.realtime.export.BtExport;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        try {
            System.err.println("Request received");
            byte[] byteArray = request().body().asBytes().toArray();

            BtExport.BusinessTransactions bts = BtExport.BusinessTransactions.parseFrom(byteArray);

            List<BtExport.BusinessTransaction> businessTransactionList = bts.getBusinessTransactionsList();
            for (BtExport.BusinessTransaction bt : businessTransactionList) {
                for (BtExport.BtOccurrence occ : bt.getOccurrencesList()) {
                    String message = String.format("%s - %s took %.3f ms to log in", bt.getName(),
                            occ.getDimensions(0), occ.getResponseTime());

                    System.err.println(message);
                }
            }
            System.err.println("Request processed");
        } catch (Exception exc) {
            System.err.println("An error occured: " + exc);
        }

        return ok("hello DT world");
    }
}
