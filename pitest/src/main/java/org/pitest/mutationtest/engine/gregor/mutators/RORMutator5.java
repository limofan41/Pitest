package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum RORMutator5 implements MethodMutatorFactory {

  ROR_MUTATOR5;

  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new RORMethodVisitor5(this, context, methodVisitor);
  }

  public String getGloballyUniqueId() {
    return this.getClass().getName();
  }

  public String getName() {
    return name();
  }

}

class RORMethodVisitor5 extends AbstractJumpMutator {

  private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

  static {
    // > to != 
    MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFNE, 
        "Replaced greater by not equal"));
    MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPNE,
        "Replaced greater by not equal"));
    
    // >= to !=
    MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFNE, 
        "Replace greater or equal by not equal"));
    MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPNE,
        "Replace greater or equal by not equal"));
    
    // < to !=
    MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFNE, 
        "Replace less by not equal"));
    MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPNE,
        "Replace less by not equal"));
        
    // <= to !=
    MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFNE, 
        "Replace less or not equal"));
    MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPNE,
        "Replace less by not equal"));
            
    // == to !=
    MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFNE,
        "Replace equal by not equal"));
    MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPNE,
        "Replace equal by not equal"));
        
    // != to ==
    MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFEQ, 
        "Replace not equal by equal"));
    MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPEQ,
        "Replace not equal by equal"));
  }

  RORMethodVisitor5(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }

}