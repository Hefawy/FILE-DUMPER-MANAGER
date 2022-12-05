package Dumper;

public class FDMTemplateFileModelDum {

	private int ID;
	private int TYPE_ID;
	private String TAG_NAME;
	private int ORDER;
	private boolean IS_CONDITIONAL_TAG;

	public FDMTemplateFileModelDum() {
		super();
	}

	public FDMTemplateFileModelDum(int ID, int TYPE_ID, String TAG_NAME, int ORDER, boolean IS_CONDITIONAL_TAG) {
		super();
		this.ID = ID;
		this.TYPE_ID = TYPE_ID;
		this.TAG_NAME = TAG_NAME;
		this.ORDER = ORDER;
		this.IS_CONDITIONAL_TAG = IS_CONDITIONAL_TAG;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(int TYPE_ID) {
		this.TYPE_ID = TYPE_ID;
	}

	public String getTAG_NAME() {
		return TAG_NAME;
	}

	public void setTAG_NAME(String TAG_NAME) {
		this.TAG_NAME = TAG_NAME;
	}

	public int getORDER() {
		return ORDER;
	}

	public void setORDER(int ORDER) {
		this.ORDER = ORDER;
	}

	public boolean isIS_CONDITIONAL_TAG() {
		return IS_CONDITIONAL_TAG;
	}

	public void setIS_CONDITIONAL_TAG(boolean IS_CONDITIONAL_TAG) {
		this.IS_CONDITIONAL_TAG = IS_CONDITIONAL_TAG;
	}

	@Override
	public String toString() {
		return "ID=" + ID + ", TYPE_ID=" + TYPE_ID + ", TAG_NAME=" + TAG_NAME + ", ORDER=" + ORDER
				+ ", IS_CONDITIONAL_TAG=" + IS_CONDITIONAL_TAG;
	}
}
