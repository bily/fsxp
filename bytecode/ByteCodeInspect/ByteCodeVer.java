public class ByteCodeVer {
	/* determine CLASS file version */
	public static void main(String[] args) {
		try {
		java.io.FileInputStream fileInputStream = 
			new java.io.FileInputStream("C:\\decompile\\classes\\fsxp\\bgl\\BaseObjectSort.class");
			
			for (int i=0;i<4;i++){
				fileInputStream.read();
			}
			for (int i=0;i<4;i++){
				System.out.println(fileInputStream.read());
			}
		}
		catch (Exception e) {}
	}

}