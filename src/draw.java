import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class draw extends JFrame
{

    BufferedImage he,im;
    static int x,y,x1,y1,w,h,flag,m1,m3;
    tray1 t=new tray1();

    public draw(BufferedImage b)

    {

        he=b;
        setBounds(0,0,1366,768);
        setLayout(new FlowLayout());
        setUndecorated (true);
        setVisible(true);


        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                x = e.getX();
                y = e.getY();

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

                    x1 = e.getX();
                    y1 = e.getY();
                    w = x1 - x;
                    h = y1 - y;
                    flag = 1;
                    repaint();

                


                ///////////////////////////////////////////////////////

                int ans= JOptionPane.showConfirmDialog(null,"Do you want to save the screenshot?","confirm",JOptionPane.YES_NO_OPTION);

                if(ans==0)
                {

                    if(tray1.path1==null)
                    {

                        JFileChooser jf = new JFileChooser();

                        jf.showSaveDialog(null);

                        try
                        {
                            File bw=jf.getSelectedFile();

                            try
                            {
                                tray1.path = bw.toString();

                                t.ind = tray1.path.indexOf('.');
                                tray1.sub = tray1.path.substring(t.ind);


                                if(!t.isValid(tray1.sub))
                                {
                                    JOptionPane.showMessageDialog(null,"Invalid Extension !");
                                    dispose();
                                    extensions r=new extensions();
                                    t.get(im);

                                }

                                else if (tray1.path != null && t.isValid(tray1.sub) )
                                {

                                    ImageIO.write(im, "jpg", new File(tray1.path));
                                    JOptionPane.showMessageDialog(null, "Screenshot saved to the selected path !");
                                    dispose();

                                }

                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Screenshot was not saved !");

                                    dispose();
                                }


                            }
                            catch(Exception c)
                            {
                                dispose();

                            }


                        }

                        catch (Exception ee)
                        {

                            ee.printStackTrace();
                        }

                    }

                    else if(tray1.path1!= null)
                    {

                        tray1.name=JOptionPane.showInputDialog(null,"Enter name with extension(.jpg/.png..)");

                        try
                        {
                            t.ind = tray1.name.indexOf('.');

                            tray1.sub = tray1.name.substring(t.ind);
                        }
                        catch (Exception er)
                        {

                            dispose();
                        }

                        if(tray1.name.indexOf('.')==-1)
                        {
                            dispose();
                        }

                        if(tray1.name==null)
                        {
                            dispose();
                        }

                        else
                        {

                            if(t.isValid(tray1.sub))
                            {

                                tray1.final_path = tray1.path1 + "\\" + tray1.name;
                                System.out.println(tray1.final_path);
                                try
                                {
                                    ImageIO.write(im, "jpg", new File(tray1.final_path));

                                }
                                catch (Exception ee)
                                {
                                    ee.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(null, "Screenshot saved to the given path !");
                                dispose();

                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Invalid extension!");
                                dispose();
                                extensions ee=new extensions();

                            }
                        }


                    }


                }
                else
                {
                    dispose();

                }


                ///////////////////////////////////////////////////////
            }

        });

    }


    @Override
    public void paint(Graphics g)
    {

        g.drawImage(he,0,0,null);
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        w=x1-x;
        h=y1-y;
        m1=(x+x1)/2;
        m3=(y+y1)/2;
        if(flag!=1)
        {
            x=y=x1=y1=w=h=0;
        }

        if(flag==1)
        {

            g2d.drawRect(x, y, w, h);
            g.setColor(Color.black);
            g.fillOval(m1,y-5,13,13);
            g.fillOval(m1,y1-5,13,13);
            g.fillOval(x-5,m3,13,13);
            g.fillOval(x1-5,m3,13,13);


            try
            {
                im = he.getSubimage(x, y, w, h);
            }
            catch (Exception ss)
            {
                System.out.println("Error!");

            }

            x=y=w=h=x1=y1=0;
        }

    }
}
