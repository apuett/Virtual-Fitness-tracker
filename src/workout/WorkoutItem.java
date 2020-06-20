package workout;

public class WorkoutItem {

		private String name,reps;
		
		public WorkoutItem(String name,String reps) {
			this.name=name;
			this.reps=reps;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name=name;
		}
		
		public String getReps() {
			return reps;
		}
		public void setReps(String reps) {
			this.reps=reps;
		}
}
