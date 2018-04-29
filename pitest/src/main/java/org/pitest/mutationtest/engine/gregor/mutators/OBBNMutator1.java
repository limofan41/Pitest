package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

/**
 * Replaces a&amp;b by a|b and the opposite
 */
public enum OBBNMutator1 implements MethodMutatorFactory {

  OBBN_MUTATOR1;

  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new OBBNMethodVisitor1(this, methodInfo, context, methodVisitor);
  }

  public String getGloballyUniqueId() {
    return this.getClass().getName();
  }

  public String getName() {
    return name();
  }

}

class OBBNMethodVisitor1 extends AbstractInsnMutator {

  OBBNMethodVisitor1(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    // integers
    MUTATIONS.put(Opcodes.IAND, new InsnSubstitution(Opcodes.IOR,
        "Replaced integer and with or"));
    MUTATIONS.put(Opcodes.IOR, new InsnSubstitution(Opcodes.IAND,
        "Replaced integer or with and"));

    // longs
    MUTATIONS.put(Opcodes.LAND, new InsnSubstitution(Opcodes.LOR,
        "Replaced long and with or"));
    MUTATIONS.put(Opcodes.LOR, new InsnSubstitution(Opcodes.LAND,
        "Replaced long or with and"));
  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}
