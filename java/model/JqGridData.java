package student.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.simple.JSONValue;


public class JqGridData<T> {

 private List<T> rows;

 public JqGridData(List<T> rows) {

  this.rows = rows;
 }


 public List<T> getRows() {
  return rows;
 }
 
 public String getJsonString(){
  Map<String, Object> map = new HashMap<>();

  map.put("rows", rows);
  return JSONValue.toJSONString(map);
 }
}