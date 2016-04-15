package Santiago.Tests.Classes;


public class TuilePlantation {

	private int idPlantation;
	private int SourceX;
	private int SourceY;
	private boolean desert;
	private boolean visible;
	private String plante;
	private Joueur joueur;
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public TuilePlantation(int idPlantation, String plante ){
		this.idPlantation=idPlantation;
		this.plante=plante;
		this.desert=false;
		this.visible=false;
	}

	public int getIdPlantation() {
		return idPlantation;
	}

	public void setIdPlantation(int idPlantation) {
		this.idPlantation = idPlantation;
	}

	public int getSourceX() {
		return SourceX;
	}

	public void setSourceX(int sourceX) {
		SourceX = sourceX;
	}

	public int getSourceY() {
		return SourceY;
	}

	public void setSourceY(int sourceY) {
		SourceY = sourceY;
	}

	public boolean isDesert() {
		return desert;
	}

	public void setDesert(boolean desert) {
		this.desert = desert;
	}

	public String getPlante() {
		return plante;
	}

	public void setPlante(String plante) {
		this.plante = plante;
	}

	
	
	
	
	
	
}
