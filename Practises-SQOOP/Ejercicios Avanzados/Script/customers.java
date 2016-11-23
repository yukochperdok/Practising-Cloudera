// ORM class for table 'customers'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 27 10:54:13 PDT 2016
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class customers extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private String customer_fname;
  public String get_customer_fname() {
    return customer_fname;
  }
  public void set_customer_fname(String customer_fname) {
    this.customer_fname = customer_fname;
  }
  public customers with_customer_fname(String customer_fname) {
    this.customer_fname = customer_fname;
    return this;
  }
  private String customer_lname;
  public String get_customer_lname() {
    return customer_lname;
  }
  public void set_customer_lname(String customer_lname) {
    this.customer_lname = customer_lname;
  }
  public customers with_customer_lname(String customer_lname) {
    this.customer_lname = customer_lname;
    return this;
  }
  private String customer_street;
  public String get_customer_street() {
    return customer_street;
  }
  public void set_customer_street(String customer_street) {
    this.customer_street = customer_street;
  }
  public customers with_customer_street(String customer_street) {
    this.customer_street = customer_street;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof customers)) {
      return false;
    }
    customers that = (customers) o;
    boolean equal = true;
    equal = equal && (this.customer_fname == null ? that.customer_fname == null : this.customer_fname.equals(that.customer_fname));
    equal = equal && (this.customer_lname == null ? that.customer_lname == null : this.customer_lname.equals(that.customer_lname));
    equal = equal && (this.customer_street == null ? that.customer_street == null : this.customer_street.equals(that.customer_street));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof customers)) {
      return false;
    }
    customers that = (customers) o;
    boolean equal = true;
    equal = equal && (this.customer_fname == null ? that.customer_fname == null : this.customer_fname.equals(that.customer_fname));
    equal = equal && (this.customer_lname == null ? that.customer_lname == null : this.customer_lname.equals(that.customer_lname));
    equal = equal && (this.customer_street == null ? that.customer_street == null : this.customer_street.equals(that.customer_street));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.customer_fname = JdbcWritableBridge.readString(1, __dbResults);
    this.customer_lname = JdbcWritableBridge.readString(2, __dbResults);
    this.customer_street = JdbcWritableBridge.readString(3, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.customer_fname = JdbcWritableBridge.readString(1, __dbResults);
    this.customer_lname = JdbcWritableBridge.readString(2, __dbResults);
    this.customer_street = JdbcWritableBridge.readString(3, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(customer_fname, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(customer_lname, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(customer_street, 3 + __off, 12, __dbStmt);
    return 3;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(customer_fname, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(customer_lname, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(customer_street, 3 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.customer_fname = null;
    } else {
    this.customer_fname = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.customer_lname = null;
    } else {
    this.customer_lname = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.customer_street = null;
    } else {
    this.customer_street = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.customer_fname) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, customer_fname);
    }
    if (null == this.customer_lname) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, customer_lname);
    }
    if (null == this.customer_street) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, customer_street);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.customer_fname) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, customer_fname);
    }
    if (null == this.customer_lname) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, customer_lname);
    }
    if (null == this.customer_street) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, customer_street);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(customer_fname==null?"null":customer_fname, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(customer_lname==null?"null":customer_lname, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(customer_street==null?"null":customer_street, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(customer_fname==null?"null":customer_fname, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(customer_lname==null?"null":customer_lname, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(customer_street==null?"null":customer_street, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.customer_fname = null; } else {
      this.customer_fname = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.customer_lname = null; } else {
      this.customer_lname = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.customer_street = null; } else {
      this.customer_street = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.customer_fname = null; } else {
      this.customer_fname = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.customer_lname = null; } else {
      this.customer_lname = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.customer_street = null; } else {
      this.customer_street = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    customers o = (customers) super.clone();
    return o;
  }

  public void clone0(customers o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("customer_fname", this.customer_fname);
    __sqoop$field_map.put("customer_lname", this.customer_lname);
    __sqoop$field_map.put("customer_street", this.customer_street);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("customer_fname", this.customer_fname);
    __sqoop$field_map.put("customer_lname", this.customer_lname);
    __sqoop$field_map.put("customer_street", this.customer_street);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("customer_fname".equals(__fieldName)) {
      this.customer_fname = (String) __fieldVal;
    }
    else    if ("customer_lname".equals(__fieldName)) {
      this.customer_lname = (String) __fieldVal;
    }
    else    if ("customer_street".equals(__fieldName)) {
      this.customer_street = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("customer_fname".equals(__fieldName)) {
      this.customer_fname = (String) __fieldVal;
      return true;
    }
    else    if ("customer_lname".equals(__fieldName)) {
      this.customer_lname = (String) __fieldVal;
      return true;
    }
    else    if ("customer_street".equals(__fieldName)) {
      this.customer_street = (String) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
