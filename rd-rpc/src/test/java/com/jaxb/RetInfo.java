package com.jaxb;

import java.io.Serializable;
/**
 * 
 *<P>
 *  TODO：说明与描述
 *<P>
 * @author Tjee
 * @CreateDate 2017年6月22日 上午10:30:33
 */
public class RetInfo  implements Serializable
{  
    /**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;
	private String name;  
    private int age;  
    public String getName()  
    {  
        return name;  
    }  
    public void setName(String name)  
    {  
        this.name = name;  
    }  
    public int getAge()  
    {  
        return age;  
    }  
    public void setAge(int age)  
    {  
        this.age = age;  
    }  
}  