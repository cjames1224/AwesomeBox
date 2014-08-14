package com.netbuilder.awesomebox;

public enum AlbumType {

	Single,Compilation,Regular;
	
	public AlbumType stringToAlbumType (String s) {
		switch (s) {
		case "Regular": return Regular;
		case "Compilation": return Compilation;
		default: return Single;
		}
	}
	
	public String albumTypeToString (AlbumType t) {
		switch (t) {
		case Regular: return "Regular";
		case Compilation: return "Compilation";
		default: return "Single";
		}
	}
}
