import javax.swing.JFrame;

public class Patients_Records extends JFrame{

	public Patients_Records() {

        initUI();
    }

    private void initUI() {
    	
		add(new SearchPanel());
		
		setResizable(false); 		// Resizable值為false時，表示生成的窗體大小是由程序員決定的，用戶不可以自由改變該窗體的大小		
		pack();   					//pack()方法是要通知frame將其尺寸設定為可以將其內部所有的元件緊致包起來的大小
		setTitle("診所病人資料系統");
		setLocationRelativeTo(null); //setLocationRelativeTo(null) 會依照視窗的 size 與螢幕的大小來計算出視窗的位置，使得他出現在螢幕中央。				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	public static void main(String[] args) {
		var ex = new Patients_Records();
        ex.setVisible(true);
	}

}
