从Bundle中获取数据的工具

  ParamData注解

  paramName为Bundle中的key,如不设置,则默认获取当前属性的名称为key
  
  如：
  
    @ParamData(paramName = "user_name")
  
    String name;
  
    @ParamData
  
    int age;


  将数据设定到Bundle

     ArrayMap<String,Object> map = new ArrayMap<>();
   
     map.put("user_name","小明");
   
     map.put("age",3);

      Bundle bundle = BundleDataHandleUtils.initParamToBundle(null,map);

      Param param = new Param(bundle);

      System.out.println(param.name);
  
      System.out.println(param.age);

使用Bundle给类中属性赋值

    class Param{

      @ParamData(paramName = "user_name")
  
      String name;
  
      @ParamData
  
      int age;


      public Param(Bundle bundle){
  
        BundleDataHandleUtils.getBundleData(this,bundle)
  
    }

  }
