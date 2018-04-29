package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class AODMutator2 implements MethodMutatorFactory {

  private final class NegateVariableVisitor extends MethodVisitor {
    private final MutationContext context;

    NegateVariableVisitor(final MutationContext context,
        final MethodVisitor delegateVisitor) {
      super(Opcodes.ASM6, delegateVisitor);
      this.context = context;
    }

    @Override
    public void visitInsn(final int opcode) {
        if ((Opcodes.LADD == opcode) || (Opcodes.LSUB == opcode) || (Opcodes.DADD == opcode)
            || (Opcodes.DSUB == opcode) 
            || (Opcodes.LMUL == opcode)  || (Opcodes.LDIV == opcode) || (Opcodes.DMUL == opcode)
            || (Opcodes.DDIV == opcode)) {
                shouldMutate();
                mv.visitInsn(Opcodes.DUP2_X2); // double values on the stack they occupy four slots
                super.mv.visitInsn(Opcodes.POP2);
                super.mv.visitInsn(Opcodes.POP2);
            } else if ((96 <= opcode) && (111 >= opcode) && shouldMutate()) {
                super.mv.visitInsn(Opcodes.SWAP);
                super.mv.visitInsn(Opcodes.POP);
            } else {
              super.visitInsn(opcode);
              }
        }

    private boolean shouldMutate() {
        final MutationIdentifier mutationId = this.context.registerMutation(new AODMutator2(),
       "Replaced operate with second operand");
        return true;
        }

  }

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new NegateVariableVisitor(context, methodVisitor);
  }

  @Override
  public String getGloballyUniqueId() {
    return this.getClass().getName();
  }

  @Override
  public String toString() {
    return "AOD_MUTATOR2";
  }

  @Override
  public String getName() {
    return toString();
  }

}

