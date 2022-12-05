package Validator;

public class FDMTypesModelVal {

	private int ID;
	private String TYPE;

	public FDMTypesModelVal() {
		super();
	}

	public FDMTypesModelVal(int iD, String tYPE) {
		super();
		ID = iD;
		TYPE = tYPE;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	@Override
	public String toString() {
		return "fdm_types_model [ID=" + ID + ", TYPE=" + TYPE + "]";
	}
}
