package net.voidarkana.yetanotherfishmod.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.checkerframework.checker.units.qual.A;

import javax.annotation.Nullable;

public class HornwortBlock extends BushBlock implements BonemealableBlock, LiquidBlockContainer {

    protected static final VoxelShape SOUTH_1 = Block.box(8, 0, 8, 15, 15, 15);
    protected static final VoxelShape SOUTH_2 = Block.box(8, 0, 1, 15, 15, 15);

    protected static final VoxelShape EAST_1 = Block.box(8, 0, 1, 15, 15, 8);
    protected static final VoxelShape EAST_2 = Block.box(1, 0, 1, 15, 15, 8);

    protected static final VoxelShape NORTH_1 = Block.box(1, 0, 1, 8, 15, 8);
    protected static final VoxelShape NORTH_2 = Block.box(1, 0, 1, 8, 15, 15);

    protected static final VoxelShape WEST_1 = Block.box(1, 0, 8, 8, 15, 15);
    protected static final VoxelShape WEST_2 = Block.box(1, 0, 8, 15, 15, 15);

    protected static final VoxelShape FULL = Block.box(1, 0, 1, 15, 15, 15);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty AMOUNT = BlockStateProperties.FLOWER_AMOUNT;

    public HornwortBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AMOUNT, Integer.valueOf(1)));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {

        if (pState.getValue(AMOUNT)<3) {
            if (pState.getValue(FACING) == Direction.NORTH){
                return pState.getValue(AMOUNT)==1 ? NORTH_1: NORTH_2;

            }else if (pState.getValue(FACING) == Direction.WEST){
                return pState.getValue(AMOUNT)==1 ? WEST_1: WEST_2;

            }else if (pState.getValue(FACING) == Direction.SOUTH){
                return pState.getValue(AMOUNT)==1 ? SOUTH_1: SOUTH_2;

            }else{
                return pState.getValue(AMOUNT)==1 ? EAST_1: EAST_2;
            }
        }else {
            return FULL;
        }

    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && pState.getValue(AMOUNT) < 4 ? true : super.canBeReplaced(pState, context);
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.isFaceSturdy(pLevel, pPos, Direction.UP) && !pState.is(Blocks.MAGMA_BLOCK);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());

        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8 ?
                blockstate.is(this) ? blockstate.setValue(AMOUNT, Integer.valueOf(Math.min(4, blockstate.getValue(AMOUNT) + 1)))
                        : this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()) : null;
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        BlockState blockstate = super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
        if (!blockstate.isAir()) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        return blockstate;
    }

    public FluidState getFluidState(BlockState p_154537_) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        int i = pState.getValue(AMOUNT);
        if (i < 4) {
            pLevel.setBlock(pPos, pState.setValue(AMOUNT, Integer.valueOf(i + 1)), 2);
        } else {
            popResource(pLevel, pPos, new ItemStack(this));
        }

    }

    @Override
    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, AMOUNT);
    }
}
