package com.mbit.top5word;

import java.util.Comparator;

import org.apache.hadoop.io.Text;

public class PalabraOcurr {
  private long ocurr = 0;
  private Text palabra = new Text();

  public PalabraOcurr(long ocurr, Text palabra) {
    super();
    this.ocurr = ocurr;
    this.palabra = palabra;
  }

  public long getOcurr() {
    return ocurr;
  }

  public void setOcurr(long ocurr) {
    this.ocurr = ocurr;
  }

  public Text getPalabra() {
    return palabra;
  }

  public void setPalabra(Text palabra) {
    this.palabra = palabra;
  }

  public static class PalabraOcurrComparator implements
      Comparator<PalabraOcurr> {

    @Override
    public int compare(PalabraOcurr pal1, PalabraOcurr pal2) {
      // TODO Auto-generated method stub
      return ((pal2.getOcurr() >= pal1.getOcurr()) ? ((pal2.getOcurr() == pal1
          .getOcurr()) ? 0 : 1) : -1);
    }

  }

}
