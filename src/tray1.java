import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;



public class tray1 extends Frame
{

    static Robot r=null;
    static Rectangle area=null;
    public  TrayIcon ti;
    static   String path1,path,final_path,name,sub;
    int ind,flag=0;
    TrayIcon[] i;
    public static SystemTray st;
    String ck=null;
    static JLabel a;
    static BufferedImage b,j;
    static ImageIcon imageIcon;
    String []ext={".jpg",".png",".jpeg",".bmp",".tif",".tiff",".gif",".eps",".raw"};



    public tray1() throws NullPointerException

    {


        JFrame frame = new JFrame();
        frame.setUndecorated (true);
        Image img= Toolkit.getDefaultToolkit().getImage("C:\\Users\\sc.jpg");
        frame.setBounds(0,0,1366,768);




        if(! SystemTray.isSupported())
        {
            JOptionPane.showMessageDialog(null,"System tray is not supported to your machine !");
        }


            st = SystemTray.getSystemTray();

            PopupMenu pm = new PopupMenu();
            MenuItem af = new MenuItem("Screenshot");
            MenuItem ex = new MenuItem("Exit");
            MenuItem m1 = new MenuItem("Set path");
            MenuItem m2 = new MenuItem("Clear path");
            MenuItem m3 = new MenuItem("About");
            MenuItem m4 = new MenuItem("Crop and save");

            Menu Sc = new Menu("Path");
            Sc.add(m1);
            Sc.add(m2);


            ex.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });


            m1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    if (flag == 0)
                    {
                        ck = JOptionPane.showInputDialog(null, " Enter a path");
                        System.out.println("ck=" + ck);
                        if (ck.equals(" "))
                        {
                            JOptionPane.showMessageDialog(null, "Invalid path !");
                            ck = JOptionPane.showInputDialog(null, " Enter a correct path");

                        }
                        else
                            {
                            flag = 1;
                            path1 = ck;
                            JOptionPane.showMessageDialog(null, "Path set! Screenshots will be saved to the given path");
                        }


                    }
                    else
                        {
                        JOptionPane.showMessageDialog(null, "Path already set! Clear it and enter new one");
                    }

                }
            });

            m2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    path1 = null;
                    flag = 0;
                    JOptionPane.showMessageDialog(null, "Path Cleared !");

                }
            });


            m3.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    /*
                    JFrame f2=new JFrame();
                    ImageIcon l=new ImageIcon(img);
                    //f2.setResizable(false);
                    f2.setBounds(300,300,300,300);
                    JLabel k=new JLabel("",l,JLabel.TOP);
                    k.setBackground(Color.black);
                    k.setBounds(100,100,100,100);
                    f2.add(k);
                    f2.setVisible(true);
                    */
                    JOptionPane.showMessageDialog(null, "Coded by Neel Huzurbazar !");
                }
            });


            m4.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {

                    try
                    {
                        r = new Robot();

                    }
                    catch (Exception a)
                    {
                        a.printStackTrace();
                    }

                    area = new Rectangle(0, 2, 1366, 768);
                    b = r.createScreenCapture(area);

                     new draw(b);         //////////////////

                }
            });

            af.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        r = new Robot();

                    }
                    catch (Exception a)
                    {
                        a.printStackTrace();
                    }


                    area = new Rectangle(0, 2, 1366, 768);
                    b = r.createScreenCapture(area);
                    imageIcon = new ImageIcon(b);


                    a = new JLabel("", null, JLabel.CENTER);
                    a = new JLabel("", imageIcon, JLabel.CENTER);
                    a.setBounds(0, 0, 1366, 768);

                    frame.setLayout(null);
                    frame.add(a);
                    frame.setVisible(true);

                    int ans = JOptionPane.showConfirmDialog(null, "Do you want to save the screenshot?", "confirm", JOptionPane.YES_NO_OPTION);

                    if (ans == 0) {

                        if (path1 == null) {

                            JFileChooser jf = new JFileChooser();

                            jf.setDialogTitle("Screenshooter");
                            setIconImage(img);
                            jf.showSaveDialog(null);


                            try {
                                File bw = jf.getSelectedFile();

                                try {
                                    path = bw.toString();

                                    ind = path.indexOf('.');
                                    sub = path.substring(ind);


                                    if (!isValid(sub))
                                    {
                                        JOptionPane.showMessageDialog(null, "Invalid Extension !");
                                        frame.dispose();
                                        a.setIcon(null);
                                        new extensions();

                                    }
                                    else if (path != null && isValid(sub))
                                    {

                                        ImageIO.write(b, "jpg", new File(path));
                                        JOptionPane.showMessageDialog(null, "Screenshot saved to the selected path !");
                                        a.setIcon(null);
                                        frame.dispose();

                                    }
                                    else
                                        {
                                        JOptionPane.showMessageDialog(null, "Screenshot was not saved !");
                                        a.setIcon(null);
                                        frame.dispose();
                                    }


                                }
                                catch (Exception c)
                                {
                                    frame.dispose();
                                }

                            }
                            catch (Exception ee)
                            {

                                ee.printStackTrace();
                            }

                        }
                        else if (path1 != null)
                        {

                            name = JOptionPane.showInputDialog(null, "Enter name with extension(.jpg/.png..)");

                            try
                            {
                                ind = name.indexOf('.');

                                sub = name.substring(ind);
                            } catch (Exception er)
                            {

                                frame.dispose();
                            }

                            if (name.indexOf('.') == -1)
                            {
                                frame.dispose();
                                a.setIcon(null);
                            }

                            if (name == null)
                            {
                                frame.dispose();
                                a.setIcon(null);
                            }
                            else
                                {

                                if (isValid(sub))
                                {

                                    final_path = path1 + "\\" + name;
                                    System.out.println(final_path);
                                    try
                                    {
                                        ImageIO.write(b, "jpg", new File(final_path));
                                        a.setIcon(null);

                                    }
                                    catch (Exception ee)
                                    {
                                        ee.printStackTrace();
                                    }
                                    JOptionPane.showMessageDialog(null, "Screenshot saved to the given path !");
                                    frame.dispose();

                                }
                                else
                                    {
                                    JOptionPane.showMessageDialog(null, "Invalid extension!");
                                    frame.dispose();
                                    a.setIcon(null);
                                     new extensions();


                                }
                            }


                        }


                    }
                    else
                        {
                        frame.dispose();
                        a.setIcon(null);

                    }

                }

            });


            pm.add(af);
            pm.add(ex);
            pm.add(Sc);
            pm.add(m3);
            pm.add(m4);


            ti = new TrayIcon(img, "Screenshooter", pm);

            ti.setImageAutoSize(true);

            try
            {
                if(!  isPresent())
                {
                    st.add(ti);
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }




    }

    public static void main(String[] args)
    {

        tray1 t=new tray1();

    }

    public boolean isValid(String p) throws IndexOutOfBoundsException
    {
        int a=0;

        for(int i=0;i<=ext.length-1;i++)
        {
            if(ext[i].equals(sub))
            {
                a=1;
                break;
            }

        }
        return a == 1;

    }

    public  boolean isPresent()
    {
        int b=0;
        i=st.getTrayIcons();
        for(TrayIcon p:i)
        {
           b=1;
        }

       return b==1;
    }

    public void get(BufferedImage v)
    {
        j=v;
    }

    public tray1(String ae)
    {

        if(ae!=" ")
        {

            if( ! (name==(null) ) )
            {
                try
                {
                    if(name.contains("."))
                    {
                        name=name.substring(0,name.indexOf('.'));
                        final_path = path1 + "\\" +  name + '.' + ae;

                    }

                    else
                    {
                        final_path = path1 + "\\" +  name + '.' + ae;

                    }

                }
                catch (Exception d)
                {
                    JOptionPane.showMessageDialog(null,"Error saving screenshot!");

                }

                try
                {

                    if(j==null)
                    {
                        ImageIO.write(b, "jpg", new File(final_path));
                    }
                    else
                    {
                        ImageIO.write(j, "jpg", new File(final_path));
                    }
                    a.setIcon(null);
                    JOptionPane.showMessageDialog(null, "Screenshot saved to the given path !");
                }

                catch (Exception ee)
                {
                    JOptionPane.showMessageDialog(null,"Error saving screenshot!");
                    a.setIcon(null);
                    //ee.printStackTrace();
                }

            }
            else if(!(path==(null)) )
            {
                try
                {
                    path=path.substring(0,path.indexOf('.'));
                    final_path = path + '.' + ae;

                }
                catch (Exception d)
                {
                    JOptionPane.showMessageDialog(null,"Error");
                }

                try
                {

                    if(j==null)
                    {
                        ImageIO.write(b, "jpg", new File(final_path));
                        a.setIcon(null);
                    }
                    else
                    {
                        ImageIO.write(j, "jpg", new File(final_path));
                        a.setIcon(null);
                    }

                    JOptionPane.showMessageDialog(null, "Screenshot saved to the given path");

                }

                catch (Exception ee)
                {
                   JOptionPane.showMessageDialog(null,"Screenshot Saved!");

                }


            }
        }


    }





}
