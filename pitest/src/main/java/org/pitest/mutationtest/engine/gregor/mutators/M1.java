package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum M1 implements MethodMutatorFactory {

    M1;
    @Override
    public MethodVisitor create(MutationContext context, MethodInfo methodInfo, MethodVisitor methodVisitor) {
        // TODO Auto-generated method stub
        return new M1mutator(context, methodVisitor, this);
    }

    @Override
    public String getGloballyUniqueId() {
        // TODO Auto-generated method stub
        return this.getClass().getName();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name();
    }
}
