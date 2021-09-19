package entities;


public class UserSapFileException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public CodeEnum code;
	
	
	public UserSapFileException(String message, CodeEnum code) {
		super(message);
		if (code == null)
			this.code = CodeEnum.BAD_REQUEST;
		else if (code.getCode() == 0)
			this.code = CodeEnum.BAD_REQUEST;
		else
			this.code = code;
	}
	
	public CodeEnum getCode() {
		return this.code;
	}
}
