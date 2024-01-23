package student.model;

public class Score {
	 private String subjectName;
	    private double mark;

		public String getSubjectName() {
			return subjectName;
		}

		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}

		public double getMark() {
			return mark;
		}

		public void setMark(double mark) {
			this.mark = mark;
		}

		
		 public String toString() {
		        return "{\"SubjectName\":\"" + subjectName + "\",  \"Mark\":\"" + mark + "\"}";
		    }
}
