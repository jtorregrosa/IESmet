package model;

import java.util.ArrayList;
import java.util.List;

public class ConnectionModel implements IModel{

	List<String> _ports;
	String _selectedPort;
	
	public ConnectionModel(){
		_ports= new ArrayList<String>();
		_selectedPort=null;
	}
}
