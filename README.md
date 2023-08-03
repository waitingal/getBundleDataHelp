  /*
  *设置传参
  */
   ArrayMap<String,Object> map = new ArrayMap<>();
   map.put("","");
   Intent intent = new Intent(content,Aactivity.class);
  BundleDataHandleUtils.initParamToIntent(intent,map);
  content.startActivity(intent);

 /*
 *取出参数
 */
  Aactivity extends Activity{
      @ParamData(paramName = "int")
      int testInt;
      @ParamData(paramName = "String")
      String p_String;
      @ParamData(paramName = "Boolean")
      Boolean mBoolean;
      @ParamData(paramName = "Double")
      Double mDouble;
      @ParamData(paramName = "Float")
      Float mFloat;
      @ParamData(paramName = "Long")
      Long mLong;
      @ParamData(paramName = "Serializable")
      Xxx mSerializable;
      @ParamData(paramName = "String[]")
      String[] mStringArr;
      @ParamData(paramName = "Serializable_Map")
      Map<String, xxx> mapBean;
      @ParamData
      List<xxx> list;
 
     private void getInteneData(){
         BundleDataHandleUtils.getIntentData(this, getIntent());
     }
  }
 
    AFragment extends Fragment{
         @ParamData(paramName = "int")
        int testInt;
        @ParamData(paramName = "String")
        String p_String;
        @ParamData(paramName = "Boolean")
        Boolean mBoolean;
        @ParamData(paramName = "Double")
        Double mDouble;
        @ParamData(paramName = "Float")
        Float mFloat;
        @ParamData(paramName = "Long")
        Long mLong;
        @ParamData(paramName = "Serializable")
        Xxx mSerializable;
        @ParamData(paramName = "String[]")
        String[] mStringArr;
        @ParamData(paramName = "Serializable_Map")
        Map<String, xxx> mapBean;
        @ParamData
        List<xxx> list;
 
        public static AFragment newInstance(ArrayMap<String,Object> map) {
           AFragment fragment = new AFragment();
           fragment.setArguments(BundleDataHandleUtils.initParamToBundle(null,map));
           return fragment;
       }
 
         @Override
      public void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          BundleDataHandleUtils.getBundleData(this,getArguments())
      }
 
    }