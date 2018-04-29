package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

/**
 * Mutation operator realizing (a) to (a--)
 */
public enum UOIMutator2 implements MethodMutatorFactory {

    UOI_MUTATOR2;

    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new UOIMethodVisitor2(this, context, methodInfo, methodVisitor);
    }

    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    public String getName() {
        return name();
    }
}

class UOIMethodVisitor2 extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext context;
    private final MethodInfo info;

    UOIMethodVisitor2(final MethodMutatorFactory factory, final MutationContext context, final MethodInfo info,
            final MethodVisitor delegateMethodVisitor) {
        super(Opcodes.ASM5, delegateMethodVisitor);
        this.factory = factory;
        this.context = context;
        this.info = info;
    }

    private boolean shouldMutate(String description) {
        if (context.getClassInfo().isEnum()) {
            return false;
        } else {
            final MutationIdentifier newId = this.context.registerMutation(this.factory, description);
            return this.context.shouldMutate(newId);
        }
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        mv.visitVarInsn(opcode, var);
        switch (opcode) {
        case Opcodes.ILOAD:
            if (this.shouldMutate("Decremented integer variable number(a--) " + var)) {
                mv.visitIincInsn(var, -1);
            }
            break;
        case Opcodes.FLOAD:
            if (this.shouldMutate("Decremented float variable number(a--) " + var)) {
                mv.visitInsn(Opcodes.DUP);
                mv.visitInsn(Opcodes.FCONST_1);
                mv.visitInsn(Opcodes.FSUB);
                mv.visitVarInsn(Opcodes.FSTORE, var);
            }
            break;
        case Opcodes.LLOAD:
            if (this.shouldMutate("Decremented long variable number(a--)  " + var)) {
                mv.visitInsn(Opcodes.DUP2);
                mv.visitInsn(Opcodes.LCONST_1);
                mv.visitInsn(Opcodes.LSUB);
                mv.visitVarInsn(Opcodes.LSTORE, var);
            }
            break;
        case Opcodes.DLOAD:
            if (this.shouldMutate("Decremented double variable number(a--) " + var)) {
                mv.visitInsn(Opcodes.DUP2);
                mv.visitInsn(Opcodes.DCONST_1);
                mv.visitInsn(Opcodes.DSUB);
                mv.visitVarInsn(Opcodes.DSTORE, var);
            }
            break;
        default:
            break;
        }
    }

 
}