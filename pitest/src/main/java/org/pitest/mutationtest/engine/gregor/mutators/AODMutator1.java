package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
//import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

/**
 * Mutator that replaces (a+b) by a; Does the same for the operators (*,/,%,-).
 */
public enum AODMutator1 implements MethodMutatorFactory {

    AOD_MUTATOR1;

    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
            final MethodVisitor methodVisitor) {
        return new AODMethodVisitor1(this, methodInfo, context, methodVisitor);
    }

    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    public String getName() {
        return name();
    }
}

class AODMethodVisitor1 extends AbstractInsnMutator {
    private final MutationContext context;

    AODMethodVisitor1(final MethodMutatorFactory factory, final MethodInfo methodInfo, final MutationContext context,
            final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
        this.context = context;
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

    static {
        MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.POP, "Replaced Integer Mul with first operand"));
        MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.POP2, "Replaced Long Mul with first operand"));
        MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.POP, "Replaced Float Mul with first operand"));
        MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.POP2, "Replaced Double Mul with first operand"));

        MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.POP, "Replaced Integer div with first operand"));
        MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.POP2, "Replaced Long div with first operand"));
        MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.POP, "Replaced Float div with first operand"));
        MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.POP2, "Replaced Double div with first operand"));

        MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.POP, "Replaced Integer ADD with first operand"));
        MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.POP2, "Replaced Long ADD with first operand"));
        MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.POP, "Replaced Float ADD with first operand"));
        MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.POP2, "Replaced Double ADD with first operand"));

        MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.POP, "Replaced Integer SUB with first operand"));
        MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.POP2, "Replaced Long SUB with first operand"));
        MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.POP, "Replaced Float SUB with first operand"));
        MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.POP2, "Replaced Double SUB with first operand"));

    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }

}