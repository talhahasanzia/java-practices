package com.playground.java8.unsorted;


// There are 2 types of copying operation in java:
// - Reference Copy   car1-car2 can reference same object by car1=car2, one change will reflect on both objects
// - Object copy are further divided into 2 types :
//      - Shallow Copy
//      - Deep Copy

public class ShallowDeepCopy {

    public void shallowDeepCopyTest()
    {

        Info originalInfo=new Info("original person","original address",new Gender(Gender.MALE));

        Info shallowCopy=new Info(originalInfo);  // copies internal objects as-is

        Info deepCopy=new Info(originalInfo, true); // copies internal objects as-new references




    }


    public class Info {
        // object copy (cloning) generally refers to clone behaviour of references inside a class and how they are cloned.
        String name;
        String address;
        Gender gender;


        public Info(String name, String address, Gender gender) {
            this.name = name;
            this.address = address;
            this.gender = gender;
        }

        // shallow copy
        public Info(Info someObject) {
            this.name = someObject.name;
            this.address = someObject.address;
            this.gender=someObject.gender;

        }


        // deep copy
        public Info(Info someObject, boolean avoidOverloadWarning) {
            this.name = new String(someObject.name);
            this.address = new String(someObject.address);
            this.gender=new Gender(Gender.MALE);

        }

    }


    public class Gender {
        public static final int MALE = 0;
        public static final int FEMALE = 1;

        int gender;

        public Gender(int gender) {
            this.gender =gender ;
        }


        public String getGender() {
            if (gender == MALE)
                return "M";
            else
                return "F";
        }
    }

}
