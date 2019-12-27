package facade;

public class Mainframe {
	private Memory m;
	private Cpu c;
	private HardDisk d;
	private OS o;
	
	public Mainframe() {
		m = new Memory();
		c = new Cpu();
		d = new HardDisk();
		o = new OS();		
	}
	
	public void on() {
		m.check();
		c.run();
		d.read();
		o.load();
		System.out.println("�����ɹ�...");
	}
	
	public void off1() {
		System.out.print("���");
		m.off();
		System.out.println("����ʧ��...");
	}
	
	public void off2() {
		System.out.print("���");
		c.off();
		System.out.println("����ʧ��...");
	}
	
	public void off3() {
		System.out.print("���");
		d.off();
		System.out.println("����ʧ��...");
	}
	
	public void off4() {
		System.out.print("���");
		o.off();
		System.out.println("����ʧ��...");
	}
}
