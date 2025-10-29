package edu.training.news_portal.beans;

public class RegistrationInfo {

	private final String email;
	private final String password;
	private final String name;
	private final String surname;

	private RegistrationInfo(RegBuider builder) {
		email = builder.getEmail();
		password = builder.getPassword();
		name = builder.getName();
		surname = builder.getSurname();
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public static class RegBuider implements Builder<RegistrationInfo> {      
																		     
																			
		private String email;
		private String password;
		private String name;
		private String surname;

		public RegBuider() {
		}

		public RegBuider setEmail(String email) {
			this.email = email;
			return this;
		}

		public RegBuider setPassword(String password) {
			this.password = password;
			return this;
		}

		public RegBuider setName(String name) {
			this.name = name;
			return this;
		}

		public RegBuider setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}

		public String getName() {
			return name;
		}

		public String getSurname() {
			return surname;
		}

		@Override
		public RegistrationInfo build() {
			return new RegistrationInfo(this);
		}

	}

}
