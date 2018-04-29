package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

import org.objectweb.asm.Label;

public class M1mutator extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext context;

    M1mutator(final MutationContext context, final MethodVisitor vistor, final MethodMutatorFactory factory) {
        super(Opcodes.ASM6, vistor);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        Label label1 = new Label();
        if (opcode == Opcodes.GETFIELD) {
            this.mv.visitInsn(Opcodes.DUP);
            this.mv.visitJumpInsn(Opcodes.IFNONNULL, label1);
            final MutationIdentifier newId = this.context.registerMutation(factory, "The object is null");
            if (this.context.shouldMutate(newId)) {
                execute(desc);
            } else {             
                this.mv.visitFieldInsn(opcode, owner, name, desc);
            }
            this.mv.visitLabel(label1);
            this.mv.visitFieldInsn(opcode, owner, name, desc);
            
        } else {
            this.mv.visitFieldInsn(opcode, owner, name, desc);
        }
    }

    public void execute(String desc) {
        if (desc.equals("I") || desc.equals("Z") || desc.equals("C") || desc.equals("B") || desc.equals("S")) {
            super.visitInsn(Opcodes.ICONST_0);
        }  else if (desc.equals("D")) {
            super.visitInsn(Opcodes.DCONST_0);
        } else if (desc.equals("F")) {
        super.visitInsn(Opcodes.FCONST_0);
        } else if (desc.equals("J")) {   //long
        super.visitInsn(Opcodes.LCONST_0); 
    } else { //string or other
        super.visitInsn(Opcodes.ACONST_NULL);
    }
    }

}
