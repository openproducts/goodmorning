package classes;

public class CityNameCode {
	private String nameCity;
	private String codeCountry;
	
	public CityNameCode(String nameCity, String codeCountry) {
		this.setNameCity(nameCity);
		this.setCodeCountry(codeCountry);
	}

	public String getNameCity() {
		return nameCity;
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}

	public String getCodeCountry() {
		return codeCountry;
	}

	public void setCodeCountry(String codeCountry) {
		this.codeCountry = codeCountry;
	}
}
