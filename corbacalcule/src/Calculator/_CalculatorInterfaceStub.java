package Calculator;


/**
* Calculator/_CalculatorInterfaceStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from src/Calculator.idl
* Tuesday, April 25, 2023 1:17:26 AM GMT+01:00
*/

public class _CalculatorInterfaceStub extends org.omg.CORBA.portable.ObjectImpl implements Calculator.CalculatorInterface
{

  public float add (float num1, float num2)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("add", true);
                $out.write_float (num1);
                $out.write_float (num2);
                $in = _invoke ($out);
                float $result = $in.read_float ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return add (num1, num2        );
            } finally {
                _releaseReply ($in);
            }
  } // add

  public float sup (float num1, float num2)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sup", true);
                $out.write_float (num1);
                $out.write_float (num2);
                $in = _invoke ($out);
                float $result = $in.read_float ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return sup (num1, num2        );
            } finally {
                _releaseReply ($in);
            }
  } // sup

  public float div (float num1, float num2)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("div", true);
                $out.write_float (num1);
                $out.write_float (num2);
                $in = _invoke ($out);
                float $result = $in.read_float ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return div (num1, num2        );
            } finally {
                _releaseReply ($in);
            }
  } // div

  public float mul (float num1, float num2)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("mul", true);
                $out.write_float (num1);
                $out.write_float (num2);
                $in = _invoke ($out);
                float $result = $in.read_float ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return mul (num1, num2        );
            } finally {
                _releaseReply ($in);
            }
  } // mul

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Calculator/CalculatorInterface:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _CalculatorInterfaceStub