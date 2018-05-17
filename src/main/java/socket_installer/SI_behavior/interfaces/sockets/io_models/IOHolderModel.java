package socket_installer.SI_behavior.interfaces.sockets.io_models;

import socket_installer.SI_parts.actionHolder.actions.string_buffer.StringBuffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public interface IOHolderModel {
    void setInStream(BufferedInputStream bufferedInputStream);
    void setOutStream(BufferedOutputStream bufferedOutputStream);
    void setStringBuffer(StringBuffer stringBuffer);
    BufferedInputStream getInStream();
    BufferedOutputStream getOutStream();
    StringBuffer getStringBuffer();
    byte[] getBytes();
}
