package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum M2 implements MethodMutatorFactory {

    M2;
    public MethodVisitor create(MutationContext context, MethodInfo methodInfo, MethodVisitor methodVisitor, ClassByteArraySource byteSource) {
        // TODO Auto-generated method stub
        return new M2mutator(context, methodVisitor, this, byteSource);
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

    @Override
    public MethodVisitor create(MutationContext context, MethodInfo methodInfo, MethodVisitor methodVisitor) {
        // TODO Auto-generated method stub
        return null;
    }
}
