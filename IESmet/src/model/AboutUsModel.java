package model;

public class AboutUsModel implements IModel{

	private String _message;
	private String _title;
	public AboutUsModel(){
		_title= "IESMet";
		_message = "Estación Meteorológica educativa controlada por Picaxe.\n " +
				"Desarrolladores:\n" +
				"Jorge Torregrosa Lloret\n" +
				"Departamento Tecnolog�a IES N�5.";
	}
	public String getMessage() {
		return _message;
	}
	public void setMessage(String _message) {
		this._message = _message;
	}
	public String getTitle() {
		return _title;
	}
	public void setTitle(String _title) {
		this._title = _title;
	}
}
