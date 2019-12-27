package abstract_factory;

public class PcFactory implements Factory {
	public Cpu produceCpu() {
		System.out.println("Pc����������һ��PcCpu...");
		return new PcCpu();
	}
	public Ram produceRam() {
		System.out.println("Pc����������һ��PcRam...");
		return new PcRam();
	}

}
