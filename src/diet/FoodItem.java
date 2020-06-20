package diet;

import java.io.Serializable;

public class FoodItem implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = -6480752101077384449L;
		
		private String Item,ServingSize;
		private double carbs,protien,fats;
		private int cal;
		
		public FoodItem(String Item,String ServingSize,double carbs,double protien,double fats,int cal) {
			this.Item=Item;
			this.ServingSize=ServingSize;
			this.carbs=carbs;
			this.protien=protien;
			this.fats=fats;
			this.cal=cal;
		}
		
		public void setItem(String Item) {
			this.Item=Item;
		}
		public String getItem() {
			return Item;
		}
		
		public void setServingSize(String ServingSize) {
			this.ServingSize=ServingSize;
		}
		public String getServingSize() {
			return ServingSize;
		}
		
		public void setCarbs(int carbs) {
			this.carbs=carbs;
		}
		public double getCarbs() {
			return carbs;
		}
		
		public void setProtien(int protien) {
			this.protien=protien;
		}
		public double getProtien() {
			return protien;
		}
		
		public void setFats(int fats) {
			this.fats=fats;
		}
		public double getFats() {
			return fats;
		}
		
		public void setCal(int cal) {
			this.cal=cal;
		}
		public int getCal() {
			return cal;
		}
}
