package ca.uwo.csd.cs2212.team10;

/**
 * This class represents one accolade 
 * @author Neeraja Murali Dharan 
 */

public class Accolade_Node {
	//Fields representing information for an accolade 
	private String description; 
	private String title;
	private boolean check;
	
	/**
	 * Accolades constructor 
	 * intializing all values to null
	 */
	public Accolade_Node(){
		this.title=" ";
		this.description=" ";
		this.check=false;
	}
	
	/**
	 * Accolades constructor 
	 * @param title String for title of accolade
	 * @param description String for description of accolade
	 * @param check Boolean to represent if user has achieved the accolade 
	 */
	public Accolade_Node(String title,String description, boolean check){
		this.title=title;
		this.description=description; 
		this.check=check;
	}
	
	/**
     * Method returns title of accolade
     * @return String Title
     */
	public String getTitle(){
		return this.title; 
	}
	
	/**
     * Method returns description of accolade
     * @return String Description
     */
	public String getDescription(){
		return this.description;
	}
	
	/**
     * Method returns check value of accolade
     * @return Boolean Check
     */
	public boolean getCheck(){
		return this.check;
	}
	
	/**
     * Method sets check of accolade
     * @param title String
     */
	public void setTitle(String title){
		this.title=title;
	}
	
	/**
     * Method sets description of accolade
     * @param description String
     */
	public void setDescription(String description){
		this.description=description;
	}
	
	/**
     * Method sets check value of accolade
     * @param check boolean
     */
	public void setCheck(boolean check){
		this.check=check;
	}
	
}
