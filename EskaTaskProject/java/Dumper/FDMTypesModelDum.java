package Dumper;

public class FDMTypesModelDum {

	private int ID;
	private String TYPE;

	public FDMTypesModelDum() {
		super();
	}

	public FDMTypesModelDum(int iD, String tYPE) {
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
