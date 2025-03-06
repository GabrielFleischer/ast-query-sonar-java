class A {
  A() {
    System.exit(0); // Noncompliant {{Remove this call to "exit" or ensure it is really required.}}
  }

  void f() {
    System.exit(0); // Noncompliant {{Remove this call to "exit" or ensure it is really required.}}
    int a = System.exit(0); // Noncompliant {{Remove this call to "exit" or ensure it is really required.}}
//          ^^^^^^^^^^^
    System.gc();             // Compliant
    exit(0);                  // Compliant
    Runtime.getRuntime().exit(3); // Noncompliant {{Remove this call to "exit" or ensure it is really required.}}
//  ^^^^^^^^^^^^^^^^^^^^^^^^^
    Object o = Runtime.getRuntime().foo;    // Compliant
    Runtime.getRuntime().foo();  // Compliant
    Runtime.getRuntime().halt(12); // Noncompliant {{Remove this call to "halt" or ensure it is really required.}}
  }

  public static void main(String[] args) {
    Runtime.getRuntime().halt(12); // Compliant
    Runtime.getRuntime().exit(15);   // Compliant
    System.exit(0);                // Compliant
  }

  private void exit(int code) {
  }
}
