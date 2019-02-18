/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybill;
 
import java.awt.*;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.Date;
 
public class HelloWorldPrinter implements Printable{
    ArrayList<Order> ordList=null;
    float total_price=0;
    String order_id;
    String PaymentMethod;
    
     void  initOrder(ArrayList<Order> ordList, String order_id, String PaymentMethod){
        this.ordList = ordList;
        this.order_id = order_id;
        this.PaymentMethod = PaymentMethod;
    }
 
    public int print(Graphics g, PageFormat pf, int page) throws
                                                   PrinterException {
       Paper paper = new Paper();
       paper.setSize(5.8 , 8.3 );
        pf.setPaper(paper);
 
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
 
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        g2d.setFont(new Font("Arial",Font.BOLD,15));
        
        g.drawString ("VITOUS MALL", 100, 10);
        g2d.setFont(new Font("Monospaced",Font.BOLD,10));
 
        g.drawString("Bahavddihpur, Patel Nagar, Akbarpur", 40, 30);
       
        g.drawString("Uttar Pradesh, Pin: 224122, Mobile: 9616454925", 10, 45);
        
        g.drawString("----------------------------------------------",10,60);
        
        g.drawString("Date: "+orderDao.getDate(new Date()), 10, 75);
        
        g.drawString("Order ID: "+order_id, 10, 90);
        
        g.drawString("Payment Method: "+PaymentMethod, 10, 105);
       
        g.drawString(String.format("%20s %3s %7s %4s %10s","Product Name","Qty","Rate","Dis%" ,"Amount"), 1, 120);
         g2d.setFont(new Font("Monospaced",Font.PLAIN,10));
        int y = 0;
        for (Order ord : ordList){ 
                y+=15;
                String product_name = ord.product_name;
                int quantity = ord.quantity;
                float price = ord.price;
                int mrp = ord.mrp;
                int discount = ord.discount;
                total_price += price;
                g.drawString(String.format("%20s %3s %7s %4s %10s", product_name,String.valueOf(quantity),String.valueOf(mrp),String.valueOf(discount),String.valueOf(price)),1,120+y);
                
        }
        g2d.setFont(new Font("Monospaced",Font.BOLD,10));
        g.drawString("Total Price: Rs."+String.valueOf(total_price), 10, 135+y);
        g2d.setFont(new Font("Monospaced",Font.PLAIN,10));
        g.drawString("	Thank You, Visit Again :) ", 80, 150+y);
 
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
    
 
    public void startPrinting(){
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
             try {
                  job.print();
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
         }
    
}