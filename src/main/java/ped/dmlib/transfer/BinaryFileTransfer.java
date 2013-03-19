package ped.dmlib.transfer;

public interface BinaryFileTransfer {
	void pull(String libraryName, String filePath);
	void push(String libraryName, String filePath);
}
