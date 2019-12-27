package abstract_factory;

public class MacFactory implements Factory {
	public Cpu produceCpu() {
		System.out.println("Mac����������һ��MacCpu...");
		return new MacCpu();
	}
	public Ram produceRam() {
		System.out.println("Mac����������һ��MacRam...");
		return new MacRam();
	}

}
