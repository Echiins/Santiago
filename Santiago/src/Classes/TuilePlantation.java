package Classes;

import java.io.Serializable;


public class TuilePlantation implements Serializable{

	private int idPlantation;
	private int SourceX;
	private int SourceY;
	private boolean desert;
	private boolean visible;
	private String plante;
	private int tag_necessaires;
	private int tag_presents;

	/***************************************************************************
	 * *******************************CONSTRUCTOR*******************************
	 ***************************************************************************/
	public TuilePlantation(int idPlantation, String plante,int tag_necessaires ){
		this.idPlantation=idPlantation;
		this.plante=plante;
		this.desert=false;
		this.visible=false;
		this.tag_necessaires=tag_necessaires;
		this.tag_presents=0;
	}

	/***************************************************************************
	 * *******************************METHODES*******************************
	 ***************************************************************************/
	//************************************GETTER************************************
	public int getIdPlantation() {
		return idPlantation;
	}

	public int getSourceX() {
		return SourceX;
	}
	public int getSourceY() {
		return SourceY;
	}
	public boolean getDesert() {
		return desert;
	}
	public String getPlante() {
		return plante;
	}
	
	public boolean getVisible() {
		return visible;
	}
	public int getTag_necessaires() {
		return tag_necessaires;
	}
	public int getTag_presents() {
			return tag_presents;
		}
	//************************************SETTER************************************
	public void setIdPlantation(int idPlantation) {
		this.idPlantation = idPlantation;
	}

	public void setSourceX(int sourceX) {
		SourceX = sourceX;
	}

	public void setSourceY(int sourceY) {
		SourceY = sourceY;
	}
	public void setDesert(boolean desert) {
		this.desert = desert;
	}
	public void setPlante(String plante) {
		this.plante = plante;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public void setTag_necessaires(int tag_necessaires) {
		this.tag_necessaires = tag_necessaires;
	}

	public void setTag_presents(int tag_presents) {
		this.tag_presents = tag_presents;
	}
}