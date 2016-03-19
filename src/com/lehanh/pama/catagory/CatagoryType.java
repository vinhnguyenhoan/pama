package com.lehanh.pama.catagory;

public enum CatagoryType {

	SERVICE(){
		public Catagory createCatalog(Long id) {
			return new ServiceCatagory(id, this);
		}
	},
	DIAGNOSE(SERVICE){
		public Catagory createCatalog(Long id) {
			return new DiagnoseCatagory(id, this);
		}
	},
	SURGERY(DIAGNOSE) {
		public Catagory createCatalog(Long id) {
			return new SurgeryCatagory(id, this);
		}
	},
	PROGNOSTIC(SERVICE) {
		public Catagory createCatalog(Long id) {
			return new PrognosticCatagory(id, this);
		}
	},
	PRESCRIPTION{
		public Catagory createCatalog(Long id) {
			return new PrescriptionCatagory(id, this);
		}
	},
	DRUG(PRESCRIPTION) {
		public Catagory createCatalog(Long id) {
			return new DrugCatagory(id, this);
		}
	}, 
	
	DR {
		public Catagory createCatalog(Long id) {
			return new DrCatagory(id, this);
		}
	},
	
	APPOINTMENT {
		public Catagory createCatalog(Long id) {
			return new AppointmentCatagory(id, this);
		}
	}, 
	SPIRIT_LEVEL {
		public Catagory createCatalog(Long id) {
			return new SpiritLevel(id, this);
		}
	}
	;
	
	private final CatagoryType parentCatagoryType;
	
	private CatagoryType() {
		this.parentCatagoryType = null;
	}
	
	private CatagoryType(CatagoryType parentCatagoryType) {
		this.parentCatagoryType = parentCatagoryType;
	}
	
	public CatagoryType getParentCatagoryType() {
		return this.parentCatagoryType;
	}

	public Catagory createCatalog(Long id) {
		return new Catagory(id, this);
	}
	
	public Catagory createCatalog(Long id, String name, String desc) {
		return new Catagory(id, this, name, desc);
	}
}