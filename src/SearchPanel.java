import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.lang.*;

public class SearchPanel extends JPanel
{
	static Scanner in = new Scanner(System.in);
	static ArrayList<String> list_PI_or = new ArrayList<>();
	static ArrayList<String> list_BR_or = new ArrayList<>();
	static ArrayList<String> list_PI_da = new ArrayList<>();
	static ArrayList<String> list_BR_da = new ArrayList<>();
	
	static String path_info = "./Patient Information.txt" ;
	static String path_blood = "./Blood Records.txt";
	
	private final int BOARD_WIDTH = 800;				
	private final int BOARD_HEIGHT = 400;
	
	public SearchPanel()
	{
		initBoard();
	}
	
	private void initBoard()
	{
		setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		setLayout(null);
	
		JLabel title  = new JLabel("請問您要查詢還是新增？");	// 增加一個標籤
		title.setBounds(320, 60, 220, 60);		// 指定標籤的位置
			
		JButton searchButton = new JButton("查詢");	// 建立按鈕，按鈕文字為查詢
		searchButton.setBounds(320, 120, 50, 25);
		searchButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
				search();
            }
        });
		
		JButton inputButton = new JButton("新增");	
		inputButton.setBounds(400, 120, 50, 25);
		inputButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
				input();
            }
        });
		add(title);
		add(searchButton);					// 將按鈕加入面板
		add(inputButton);
	}
	
	public void search() 
	{	
		JButton north = new JButton("首頁");
		north.setBounds(0, 0, BOARD_WIDTH, 20);
		north.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
            	initBoard();
            	repaint();
            }
        });
		
		try
		{
		FileReader fr_info = new FileReader(path_info);    		//建立檔案內容丟到實體fr
	    BufferedReader br_info = new BufferedReader(fr_info);		 //將實體fr丟到讀取器br
	    
	    FileReader fr_blood = new FileReader(path_blood);    		
	    BufferedReader br_blood = new BufferedReader(fr_blood);	
	    
	    Scanner inputfile_info = new Scanner(Paths.get(path_info));
		Scanner inputfile_blood = new Scanner(Paths.get(path_blood));
	    
	    	try
	    	{
	    		String str_1 = null , str_2 = null;
	    		while((str_1 = br_info.readLine()) != null)
	    		{
	    			list_PI_da.add(str_1);
	    		}
	    		while((str_2 = br_blood.readLine()) != null)
	    		{
	    			list_BR_da.add(str_2);
	    		}
	    		
	    		while(inputfile_info.hasNext())
	    		{
	    			list_PI_or.add(inputfile_info.next());
	    		}
	    		while(inputfile_blood.hasNext())
	    		{
	    			list_BR_or.add(inputfile_blood.next());
	    		}
	    	}
	    	catch(IllegalStateException illegalstateexceptio)
	    	{	
	            JOptionPane.showMessageDialog(null, "檔案資料匯入錯誤","提醒",JOptionPane.WARNING_MESSAGE);
	            System.exit(0);
	    	}
		}
		catch(IOException ioexception)
		{
			JOptionPane.showMessageDialog(null, "找不到檔案","錯誤",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
		}
		
		JLabel title  = new JLabel("請問您要查詢病人資料還是血壓/血糖資料？");	// 增加一個標籤
		title.setBounds(265, 70, 300, 50);		// 指定標籤的位置
			
		JButton patientButton = new JButton("病人資料");	// 建立按鈕，按鈕文字為查詢
		patientButton.setBounds(270, 150, 80, 30);
		patientButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	SearchPatient();
            }
        });
		
		JButton bloodButton = new JButton("血壓/血糖資料");	
		bloodButton.setBounds(400, 150, 115, 30);
		bloodButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	SearchBR();
            }
        });
		add(north);
		add(title);
		add(patientButton);	
		add(bloodButton);
		
		repaint();
	}
	
	public void input() 
	{
		JButton north = new JButton("首頁");
		north.setBounds(0, 0, BOARD_WIDTH, 20);
		north.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
            	initBoard();
            	repaint();
            }
        });
		
		JLabel title  = new JLabel("請問您要輸入個人資料還是血壓/血糖資料？");	// 增加一個標籤
		title.setBounds(265, 70, 300, 50);		// 指定標籤的位置
			
		JButton patientButton = new JButton("病人資料");	// 建立按鈕，按鈕文字為查詢
		patientButton.setBounds(270, 150, 80, 30);
		patientButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
            	InputPatient();
            }
        });
		
		JButton bloodButton = new JButton("血壓/血糖資料");	
		bloodButton.setBounds(400, 150, 115, 30);
		bloodButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
            	InputBR();
            }
        });
		add(north);
		add(title);
		add(patientButton);	
		add(bloodButton);
		
		repaint();
	}
	
	public void SearchPatient() 
	{
		JButton north = new JButton("首頁");
		north.setBounds(0, 0, BOARD_WIDTH, 20);
		north.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
            	initBoard();
            	repaint();
            }
        });
		
		JLabel num = new JLabel("請輸入欲查詢之病歷號？");
		num.setBounds(100, 190, 170, 30);
		
		JTextField number = new JTextField(0);	// 建立一個輸入框，不預設寬度
		number.setBounds(270, 190, 100, 25); // 指定輸入框的位置
		
		JButton con = new JButton("確認");
		con.setBounds(375, 190, 50, 25);
		con.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	
            	double BMI = 0;
        		String anshyper,ansdia,ansheart;
        		boolean sto_1 = true;

        		for(int i = 0; i < list_PI_or.size() ; i++)
        		{	
        			if(number.getText().equals((String)list_PI_or.get(i)))
        			{	
        				String hyper = list_PI_or.get(i+5);
        				String dia = list_PI_or.get(i+6);
        				String heart = list_PI_or.get(i+7);
        					
        				if(hyper.equals("是"))
        					anshyper = "有";
        				else
        					anshyper = "無";
        					
        				if(dia.equals("是"))
        					ansdia = "有";
        				else
        					ansdia = "無";
        					
        				if(heart.equals("是"))
        					ansheart = "有";
        				else
        					ansheart = "無";
        					
        				double tallm = Double.parseDouble((String)list_PI_or.get(i+3))/100;
        				BMI = Double.parseDouble((String)list_PI_or.get(i+4))/(tallm*tallm);
        				
        				removeAll();
        				
        				JButton north = new JButton("首頁");
        				north.setBounds(0, 0, BOARD_WIDTH, 20);
        				north.addActionListener(new ActionListener() {
        		            
        		            public void actionPerformed(ActionEvent e) {
        		            	removeAll();
        		            	initBoard();
        		            	repaint();
        		            }
        		        });
        				
        				JLabel label = new JLabel("個人資料：");
        				label.setBounds(355, 130, 120, 40);		//x, y ,width, height
        				label.setFont(new Font("細明體", Font.BOLD , 22));
        				
        				JLabel label_de = new JLabel("姓名：" + list_PI_or.get(i+1) + "，生日：" + list_PI_or.get(i+2) +   
        						"，身高："  + list_PI_or.get(i+3) +"，體重："  + list_PI_or.get(i+4) + 
        						"，BMI：" + Math.round(BMI*100.0)/100.0);
        				label_de.setBounds(160, 170, 610, 25);
        				label_de.setFont(new Font("細明體", Font.PLAIN , 16));
        				JLabel label_det = new JLabel(anshyper + "高血壓，" + ansdia +"糖尿病，" + ansheart +"心臟病，" +
    							"紀錄：" + list_PI_or.get(i+8));
        				label_det.setBounds(160, 190, 610, 25);
        				label_det.setFont(new Font("細明體", Font.PLAIN , 16));
        				
        				add(north);
        				add(label);       	add(label_de);       add(label_det);
        				repaint();
        				
        				sto_1 = true;
        				break;
        			}
        			else
        			{
        				sto_1 = false;
        			}
        		}

        		if(sto_1 == false)
        		{	
        			JLabel label = new JLabel(number.getText() + " 此病歷未建檔，是否要新建檔案?");
    				label.setBounds(220, 220, 320, 25);
    				
    				JButton p = new JButton("是");
    				p.setBounds(250, 250, 20, 20);
    				p.addActionListener(new ActionListener() {
    		            
    		            public void actionPerformed(ActionEvent e) {
    		            	InputPatient();
    		            }
    		        });
    				JButton n = new JButton("否");
    				n.setBounds(310, 250, 20, 20);
    				n.addActionListener(new ActionListener() {
    		            
    		            public void actionPerformed(ActionEvent e) {
    		            	removeAll();
    		            	initBoard();
    		            	repaint();
    		            }
    		        });
        			add(label);
    				add(p);   		add(n);
    				repaint();
        		} 	
            }
        });
		add(north);
		add(num);		add(number);
		add(con);
		
		repaint();
	}
	
	public void SearchBR() 
	{
		JButton north = new JButton("首頁");
		north.setBounds(0, 0, BOARD_WIDTH, 20);
		north.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
            	initBoard();
            	repaint();
            }
        });
		
		JLabel num = new JLabel("請輸入欲查詢之病歷號？");
		num.setBounds(395, 180, 200, 30);
		
		JTextField number = new JTextField(0);	// 建立一個輸入框，不預設寬度
		number.setBounds(395, 210, 100, 25); // 指定輸入框的位置
		
		JLabel da = new JLabel("請輸入欲查詢之日期？(yyyy/m/dd)");
		da.setBounds(395, 235, 300, 30);
		
		JTextField date = new JTextField(0);
		date.setBounds(395, 265, 100, 25); 
		
		JButton con = new JButton("確認");
		con.setBounds(420, 300, 50, 25);
		con.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
		
            	String name = "";
        		boolean sto_2 = true;
        		
        		for(int i = 0 ; i < list_PI_or.size() ; i++)
        		{
        			if(number.getText().equals((String)list_PI_or.get(i)))
        				name = list_PI_or.get(i+1);
        		}
        		
        		for(int i = 0 ; i < list_BR_or.size() ; i++)
        		{
        			if(number.getText().equals(list_BR_or.get(i)) && date.getText().equals(list_BR_or.get(i+1)))
        			{	
        				removeAll();
        				
        				JButton north = new JButton("首頁");
        				north.setBounds(0, 0, BOARD_WIDTH, 20);
        				north.addActionListener(new ActionListener() {
        		            
        		            public void actionPerformed(ActionEvent e) {
        		            	removeAll();
        		            	initBoard();
        		            	repaint();
        		            }
        		        });
        				JLabel label = new JLabel("血壓/血糖紀錄：");
        				label.setBounds(335, 130, 450, 30);
        				label.setFont(new Font("細明體", Font.BOLD , 22));
        				
        				JLabel label_de = new JLabel(name + " 您在 " + date.getText() + "  " +list_BR_or.get(i+2));
        				label_de.setBounds(250, 170, 610, 25);
        				label_de.setFont(new Font("細明體", Font.PLAIN, 16));
        				
        				JLabel label_det = new JLabel("舒張壓：" + list_BR_or.get(i+3) +"，收縮壓："+ list_BR_or.get(i+4) + 
        						"，脈搏：" + list_BR_or.get(i+5) +"，血糖："+ list_BR_or.get(i+6));
        				label_det.setBounds(250, 190, 610, 25);
        				label_det.setFont(new Font("細明體", Font.PLAIN, 16));
        				
        				add(north);
        				add(label);			add(label_de);			add(label_det);
        				repaint();
        				
        				sto_2 = true;
        				break;
        			}
        			else
        			{
        				sto_2 = false;
        			}
        		}
        		if(sto_2 == false)
        		{
        			JButton north = new JButton("首頁");
        			north.setBounds(0, 0, BOARD_WIDTH, 20);
        			north.addActionListener(new ActionListener() {
        	            
        	            public void actionPerformed(ActionEvent e) {
        	            	removeAll();
        	            	initBoard();
        	            	repaint();
        	            }
        	        });
        			
        			JLabel label = new JLabel("未有測量紀錄");
    				label.setBounds(405, 340, 120, 25);
    				
        			add(north);
    				add(label);
    				
    				repaint();
        		}
            	
            }
        });
		add(north);
		add(num);		add(number);
		add(da);		add(date);
		add(con);
		
		repaint();
	}
	
	public void InputPatient() 
	{
		removeAll();
		
		JButton north = new JButton("首頁");
		north.setBounds(0, 0, BOARD_WIDTH, 20);
		north.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
            	initBoard();
            	repaint();
            }
        });
		
		JLabel num = new JLabel("請輸入病歷號");
		num.setBounds(20, 110, 100, 25);
		
		JTextField number = new JTextField(0);	// 建立一個輸入框，不預設寬度
		number.setBounds(20, 135, 100, 30); // 指定輸入框的位置
		
		JLabel na = new JLabel("請輸入病人姓名");
		na.setBounds(180,110, 100, 25);
		
		JTextField name = new JTextField(0);	
		name.setBounds(180, 135, 100, 30); 
		
		JLabel bd = new JLabel("請輸入出生年月日(yyyy/mm/dd)");
		bd.setBounds(330, 110, 200, 25);
		
		JTextField birth = new JTextField(0);	
		birth.setBounds(330, 135, 150, 30);
		
		JLabel h = new JLabel("請輸入身高");
		h.setBounds(560, 110, 100, 25);
		
		JTextField height = new JTextField(0);	
		height.setBounds(555, 135, 80, 30);
		
		JLabel w = new JLabel("請輸入體重");
		w.setBounds(675, 110, 100, 25);
		
		JTextField weight = new JTextField(0);	
		weight.setBounds(670, 135, 80, 30);
		
		JLabel hy = new JLabel("是否有高血壓(是/否)");
		hy.setBounds(20, 200, 160, 25);
		
		JTextField hyper = new JTextField(0);	
		hyper.setBounds(20, 225, 100, 30);
		
		JLabel di = new JLabel("是否有糖尿病(是/否)");
		di.setBounds(180, 200, 160, 25);
		
		JTextField dia = new JTextField(0);	
		dia.setBounds(180, 225, 100, 30);
		
		JLabel hea = new JLabel("是否有心臟病(是/否)");
		hea.setBounds(330, 200, 160, 25);
		
		JTextField heart = new JTextField(0);	
		heart.setBounds(330, 225, 100, 30);
		
		JLabel re = new JLabel("紀錄");
		re.setBounds(500, 200, 100, 25);
		
		JTextField record = new JTextField(0);	
		record.setBounds(495, 225, 270, 100);
		
		JButton con = new JButton("確認");
		con.setBounds(500, 335, 30, 25);
		con.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	
            	try
        		{
        			FileWriter fw_info = new FileWriter(path_info,true);  
        			BufferedWriter bufw_info = new BufferedWriter(fw_info);
        			
        			bufw_info.newLine();
        			bufw_info.write(number.getText() + "\t");
        			bufw_info.write(name.getText() + "\t");
        			bufw_info.write(birth.getText() + "\t");
          			bufw_info.write(height.getText() + "\t");
        			bufw_info.write(weight.getText() + "\t");
        			bufw_info.flush();
        			
        			bufw_info.write(hyper.getText() + "\t");
        			bufw_info.write(dia.getText() + "\t");
        			bufw_info.write(heart.getText() + "\t");
        			bufw_info.write(record.getText());
        			bufw_info.flush();
        			
        			bufw_info.close();
        			fw_info.close();
        			
        			JLabel label = new JLabel("病患資料建立成功");
        			label.setBounds(555, 335, 200, 25);
        			
        			add(label);
        			repaint();
        		}
        		catch(IOException ioexception)
        		{
        			JOptionPane.showMessageDialog(null, "找不到可匯入之檔案","錯誤",JOptionPane.ERROR_MESSAGE);
        		}
            }
        });
		add(north);
		add(num);		add(number);		add(na);		add(name);
		add(bd);		add(birth);			add(h);			add(height);
		add(w);			add(weight);		add(hy);		add(hyper);
		add(di);		add(dia);			add(hea);		add(heart);
		add(re);		add(record);
		add(con);
		
		repaint();
	}
	
	public void InputBR() 
	{
		removeAll();
		
		JButton north = new JButton("首頁");
		north.setBounds(0, 0, BOARD_WIDTH, 20);
		north.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	removeAll();
            	initBoard();
            	repaint();
            }
        });
		
		JLabel num = new JLabel("請輸入病歷號");
		num.setBounds(65, 115, 100, 25);
		
		JTextField number = new JTextField(0);	// 建立一個輸入框，不預設寬度
		number.setBounds(65, 140, 100, 30); // 指定輸入框的位置
		
		JLabel da = new JLabel("請輸入日期(yyyy/mm/dd)");
		da.setBounds(245,115, 200, 25);
		
		JTextField date = new JTextField(0);	
		date.setBounds(245, 140, 130, 30); 
		
		JLabel t = new JLabel("請輸入時間(ex:14:00)");
		t.setBounds(500, 115, 200, 25);
		
		JTextField time = new JTextField(0);	
		time.setBounds(500, 140, 100, 30);
		
		JLabel db = new JLabel("請輸入舒張壓");
		db.setBounds(35, 210, 160, 25);
		
		JTextField dbp = new JTextField(0);	
		dbp.setBounds(35, 235, 80, 30);
		
		JLabel sb = new JLabel("請輸入收縮壓");
		sb.setBounds(195, 210, 160, 25);
		
		JTextField sbp = new JTextField(0);	
		sbp.setBounds(195, 235, 80, 30);
		
		JLabel pul = new JLabel("請輸入脈搏");
		pul.setBounds(345, 210, 160, 25);
		
		JTextField pulse = new JTextField(0);	
		pulse.setBounds(345, 235, 80, 30);
		
		JLabel fb = new JLabel("請輸入血糖");
		fb.setBounds(495, 210, 100, 25);
		
		JTextField fbg = new JTextField(0);	
		fbg.setBounds(495, 235, 80, 30);
		
		JButton con = new JButton("確認");
		con.setBounds(625, 235, 50, 30);
		con.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	
            	try
        		{
        			FileWriter fw_blood = new FileWriter(path_blood,true);  
        			BufferedWriter bufw_blood = new BufferedWriter(fw_blood);
        			
        			bufw_blood.newLine();
        			bufw_blood.write(number.getText() + "\t");
        			bufw_blood.write(date.getText() + "\t");
        			bufw_blood.write(time.getText()+ "\t");
        			bufw_blood.flush();
        			
        			bufw_blood.write(dbp.getText() + "\t");
          			bufw_blood.write(sbp.getText() + "\t");
           			bufw_blood.write(pulse.getText() + "\t");
           			bufw_blood.write(fbg.getText());
        			bufw_blood.flush();
        			
        			bufw_blood.close();
        			fw_blood.close();
        			
        			JLabel label = new JLabel("血壓/血糖資料建立成功");
        			label.setBounds(535, 285, 200, 25);
        			
        			add(label);
        			repaint();
        		}
        		catch(IOException ioexception)
        		{
        			JOptionPane.showMessageDialog(null, "找不到可匯入之檔案","錯誤",JOptionPane.ERROR_MESSAGE);
        		}
            }
        });
		add(north);
		add(num);		add(number);	add(da);		add(date);
		add(t);			add(time);		add(db);		add(dbp);
		add(sb);		add(sbp);		add(pul);		add(pulse);
		add(fb);		add(fbg);
		add(con);
		
		repaint();
	}
}
