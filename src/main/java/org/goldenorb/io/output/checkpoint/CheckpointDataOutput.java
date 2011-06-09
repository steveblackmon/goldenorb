package org.goldenorb.io.output.checkpoint;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URI;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.goldenorb.conf.OrbConfigurable;
import org.goldenorb.conf.OrbConfiguration;
import org.omg.CORBA.Any;
import org.omg.CORBA.DataOutputStream;
import org.omg.CORBA.Object;
import org.omg.CORBA.TypeCode;

/**
 *
 */
public class CheckpointDataOutput implements DataOutput, OrbConfigurable, Closeable {
  
  private OrbConfiguration orbConf;
  private FSDataOutputStream outstream;
  
  public CheckpointDataOutput(OrbConfiguration orbConf, int superStep, int partition) throws IOException {
    this.setOrbConf(orbConf);
    String outpath   = this.orbConf.getFileOutputPath() + "/" + this.orbConf.getJobNumber() + "/" + superStep + "/"
                       + partition + "/SS" + superStep + "Part" + partition;
    FileSystem fs    = FileSystem.get(URI.create(outpath), orbConf);
    OutputStream out = fs.create(new Path(outpath), true);
    this.outstream   = new FSDataOutputStream(out, FileSystem.getStatistics(outpath, FileSystem.class));
    
  }
  
  
  public void write(int b) throws IOException {
    outstream.write(b);
  }
  
  public void write(byte[] b) throws IOException {
    outstream.write(b);
  }
  
  public void write(byte[] b, int off, int len) throws IOException {
    outstream.write(b, off, len);
  }
  
  public void writeBoolean(boolean v) throws IOException {
    outstream.writeBoolean(v);
  }
  
  public void writeByte(int v) throws IOException {
    outstream.writeByte(v);
  }
  
  public void writeShort(int v) throws IOException {
    outstream.writeShort(v);
  }
  
  public void writeChar(int v) throws IOException {
    outstream.writeChar(v);
  }
  
  public void writeInt(int v) throws IOException {
    outstream.writeInt(v);
  }
  
  public void writeLong(long v) throws IOException {
    outstream.writeLong(v);
  }
  
  public void writeFloat(float v) throws IOException {
    outstream.writeFloat(v);
  }
  
  public void writeDouble(double v) throws IOException {
    outstream.writeDouble(v);
  }
  
  public void writeBytes(String s) throws IOException {
    outstream.writeBytes(s);
  }
  
  public void writeChars(String s) throws IOException {
    outstream.writeChars(s);
  }
  
  public void writeUTF(String s) throws IOException {
    outstream.writeUTF(s);
  }
  
  public void close() throws IOException {
    outstream.close();
  }

  @Override
  public void setOrbConf(OrbConfiguration orbConf) {
    this.orbConf = orbConf;
  }

  @Override
  public OrbConfiguration getOrbConf() {
    return orbConf;
  }
  
}