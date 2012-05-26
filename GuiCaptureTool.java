import java.io.*;
import java.awt.Robot;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import javax.swing.*;

/*
class GuiCamera{

}
*/

class Capture{
	private String name;
	private String defaultname="Test";
	private String imageFormat;
	private String imageDefaultFormat="png";
	public int beginx;
	public int beginy;
	public int endx;
	public int endy;
	public static int num;
	private Dimension d=Toolkit.getDefaultToolkit().getScreenSize();

	Capture(){
		name=defaultname;
		imageFormat=imageDefaultFormat;

	}
	Capture(String name,String imageFormat){
		this.name=name;
		this.imageFormat=imageFormat;

	}
	public void set(int x1,int y1,int x2,int y2){
		beginx=x1;
		beginy=y1;
		endx=x2;
		endy=y2;

	}
	void snap(){
		try{
			BufferedImage screenshot=(new Robot()).createScreenCapture(new Rectangle(beginx,beginy,endx,endy));
			name=name+num+"."+imageFormat;
			File f=new File(name);
			System.out.println("SaveFile"+"..."+name);
			ImageIO.write(screenshot,imageFormat,f);
			System.out.println("Finished...");
		}catch(Exception ex){
			System.out.println(ex);

		}
	}

}

class GuiCaptureTool { 
	private JFrame man;
	private JButton begin;
	private JButton cancel;
	private boolean done=false;
	public Capture captrue;
	GuiCaptureTool(){
		captrue=new Capture();
		man=new JFrame("Captrue Tool");
		begin=new JButton("Begin");
		cancel=new JButton("cancel");
		init();
	}
	void init(){
		man.setBounds(50,50,200,100);
		man.setLayout(new FlowLayout());
		man.add(begin);
		man.add(cancel);
		man.setResizable(false);
		man.setVisible(true);
		addKeyListener();

	}
	public void addKeyListener(){
		man.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		man.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int x1=(int)java.awt.MouseInfo.getPointerInfo().getLocation().getX();
				int y1=(int)java.awt.MouseInfo.getPointerInfo().getLocation().getY();
				int x2=(int)java.awt.MouseInfo.getPointerInfo().getLocation().getX();
				int y2=(int)java.awt.MouseInfo.getPointerInfo().getLocation().getY();
				captrue.set(x1,y1,x2,y2);




			}
		});
		begin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				done=true;
				System.out.println("Begin"+done);
				captrue.snap();
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				done=false;
				System.out.println("cancel"+done);

			}
		});
	}
	public static void main(String[] args) {
		new GuiCaptureTool();
	}

}