import processing.core.PApplet;
import processing.core.PFont;


/**
 * Created by garym on 24/04/2017.
 */
public class Ticket extends Main{

    PApplet parent;
    int PrimaryColour = color(255,20,147);

    Ticket(PApplet p)
    {
        parent = p;
    }

    public void HoverTicket(int xPos, int yPos, int rectWidth, int rectHeight, String type, PFont title)
    {
        parent.fill(PrimaryColour);
        parent.rect(xPos,yPos, rectWidth, rectHeight);
        parent.fill(255);
        parent.textFont(title, 22);
        parent.text(type, xPos+ 65, yPos + 30);

        if(parent.mouseX >= xPos && parent.mouseX <= xPos + rectWidth && parent.mouseY >= yPos && parent.mouseY <= yPos + rectHeight) {

            if (parent.mousePressed == true) {

                if (type == "Adult") {
                    adultTickets++;
                    //System.out.print(adultTickets);
                } else if (type == "Student") {
                    studentTickets++;
                } else {
                    childTickets++;
                }
                ticketCount++;
            }
        }
    }
}
