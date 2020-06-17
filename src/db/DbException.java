package db;

public class DbException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	//--Creamos el Constructor--//
	public DbException(String msg) {
		super(msg);
	}

}
