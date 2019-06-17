/**
 * 
 */
package entity;

import java.io.Serializable;

/**
 * @author Hasan
 *
 * 
 */
public class File implements Serializable {
	private String Description=null;
	private String fileName=null;	
	private int size=0;
	public  byte[] fileBytes;
	
	
	public void initArray(int size)
	{
		fileBytes = new byte [size];	
	}
	
	public File(String fileName) {
		this.fileName = fileName;
	}
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public byte[] getMybytearray() {
		return fileBytes;
	}
	
	public byte getMybytearray(int i) {
		return fileBytes[i];
	}

	public void setMybytearray(byte[] mybytearray) {
		
		for(int i=0;i<mybytearray.length;i++)
		this.fileBytes[i] = mybytearray[i];
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}	
}
